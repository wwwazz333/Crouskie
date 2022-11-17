package crouskiebackoffice.model.dao;

import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DAOProduct extends DAO<Product> {

    @Override
    protected String getRequestForAllData() {
        //set requet permet de récupérer les info d'un produit en faison la liasion avec
        //  la collection (id, name) ces valeurs seront null si il n'appartien au aucun collection
        //  les taille existante qui sont concaténer en une String sous la form  :  idsize, namesize;;;idsize2, namesize2
        //  les couleur existante qui sont concaténer en une String sous la form :  namecolor;;;namecolor2
        return "SELECT DISTINCT IDPROD, NAMEPROD, DESCRIPTIONPROD, PRICEPROD, IDCOLLECTION,\n"
                + "                case \n"
                + "                	when IDCOLLECTION is null then null\n"
                + "                	else namecollection\n"
                + "                    end as NAMECOLLECTION,\n"
                + "                (SELECT group_concat(CONCAT(idsize, ',,,', namesize) SEPARATOR';;;') FROM PRODUCT NATURAL JOIN EXISTINGSIZE NATURAL JOIN CLOTH_SIZE WHERE P1.IDPROD = IDPROD) as size_existing,\n"
                + "                (SELECT group_concat(namecolor SEPARATOR';;;') FROM PRODUCT NATURAL JOIN EXISTINGCOLOR WHERE P1.IDPROD = IDPROD) as color_existing,\n"
                + "                (SELECT group_concat(CONCAT(idtag, ',,,', nametag) SEPARATOR';;;') FROM PRODUCT NATURAL JOIN TAGS_PRODUCT NATURAL JOIN TAG WHERE P1.IDPROD = IDPROD) as tags\n"
                + "                FROM `PRODUCT` P1 NATURAL LEFT OUTER JOIN EXISTINGSIZE NATURAL LEFT OUTER JOIN EXISTINGCOLOR NATURAL LEFT OUTER JOIN COLLECTION";
    }

    @Override
    protected String getTableName() {
        return "PRODUCT";
    }

    @Override
    protected Product parseData(HashMap<String, Object> obj) {
        List<ClothSize> size_existing = new LinkedList<>();
        List<Color> color_existing = new LinkedList<>();
        List<Tag> tags = new LinkedList<>();

        if (obj.get("size_existing") != null) {
            for (String sizeLine : obj.get("size_existing").toString().split(";;;")) {
                int id = Integer.parseInt(sizeLine.split(",")[0]);
                String name = sizeLine.split(",,,")[1];
                size_existing.add(new ClothSize(id, name));
            }
        }
        if (obj.get("color_existing") != null) {
            for (String name : obj.get("color_existing").toString().split(";;;")) {
                color_existing.add(new Color(name));
            }
        }
        if (obj.get("tags") != null) {
            for (String sizeLine : obj.get("tags").toString().split(";;;")) {
                int id = Integer.parseInt(sizeLine.split(",")[0]);
                String name = sizeLine.split(",,,")[1];
                tags.add(new Tag(id, name));
            }
        }

        return new Product((int) obj.get("idprod"), obj.get("nameprod").toString(), obj.get("descriptionprod").toString(), (float) obj.get("priceprod"),
                (obj.get("idcollection") != null) ? new Collection((int) obj.get("idcollection"), obj.get("namecollection").toString()) : null,
                color_existing, size_existing, tags);
    }

    public Boolean setNameOf(Product product, String newName) throws SQLException {
        return setNameOf(product.getId(), newName);
    }

    public Boolean setNameOf(int idProduct, String newName) throws SQLException {
        Object[] args = {newName, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET nameprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setDescriptionOf(Product product, String newDescription) throws SQLException {
        return setDescriptionOf(product.getId(), newDescription);
    }

    public Boolean setDescriptionOf(int idProduct, String newDescription) throws SQLException {
        Object[] args = {newDescription, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET descriptionprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setPriceOf(Product product, Float newPrice) throws SQLException {
        return setPriceOf(product.getId(), newPrice);
    }

    public Boolean setPriceOf(int idProduct, Float newPrice) throws SQLException {
        Object[] args = {newPrice, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET priceprod = ? WHERE idprod = ?", args) == 1;
    }

    @Override
    public Boolean insertOrUpdate(Product product) throws SQLException {
        if (exist(product)) {
            Object[] idArg = {product.getId()};
            super.execute("DELETE FROM TAGS_PRODUCT WHERE idprod = ?", idArg);
            super.execute("DELETE FROM EXISTINGCOLOR WHERE idprod = ?", idArg);
            super.execute("DELETE FROM EXISTINGSIZE WHERE idprod = ?", idArg);
            Object[] args2 = {product.getName(), product.getDescription(), product.getPrice(),
                (product.getCollection() != null ? product.getCollection().getId() : null),
                product.getId()};
            super.execute("UPDATE " + getTableName() + " SET nameprod = ?, descriptionprod = ?, priceprod = ?, idcollection = ? WHERE idprod = ?", args2);
            return insertAll(product);

        } else {
            insertAll(product);

            Object[] args = {product.getName(), product.getDescription(), product.getPrice(), product.getCollection().getId()};
            return super.execute("INSERT INTO " + getTableName() + " (nameprod, descriptionprod, priceprod, idcollection) VALUES (?, ?, ?, ?)", args) == 0;
        }
    }

    private Boolean insertAll(Product product) throws SQLException {
        List<Integer> idsTag = new LinkedList<>();

        System.out.println(Arrays.toString(product.getTags().toArray()));
        for (Tag tag : product.getTags()) {
            idsTag.add(tag.getId());
        }

        List<Integer> idsSize = new LinkedList<>();
        for (ClothSize clothSize : product.getExistingSize()) {
            idsSize.add(clothSize.getId());
        }

        insertAll(product.getId(), "TAGS_PRODUCT", "idtag", idsTag.toArray());
        insertAll(product.getId(), "EXISTINGSIZE", "idsize", idsSize.toArray());
        insertAll(product.getId(), "EXISTINGCOLOR", "namecolor", product.getExistingColor().toArray());
        return true;
    }

    public Boolean insertAll(int id, String nameTable, String name, Object[] valuesName) throws SQLException {
        if (valuesName.length <= 0) {
            return true;
        }
        StringBuilder ptsInterogration = new StringBuilder();

        Object[] args = new Object[valuesName.length * 2];
        for (int i = 0; i < valuesName.length; i++) {
            if (i == 0) {
                ptsInterogration.append("(?, ?)");
            } else {
                ptsInterogration.append(", (?, ?)");
            }

            args[i * 2] = valuesName[i].toString();
            args[i * 2 + 1] = id;
        }

        return super.execute("INSERT INTO " + nameTable + " (" + name + ", idprod) values " + ptsInterogration.toString(), args) == 1;
    }

    @Override
    public Boolean exist(Product product) {
        return product != null && product.getId() != -1;
    }

    @Override
    public Boolean remove(Product obj) throws SQLException {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idprod = ? ", args) == 1;
    }
}
