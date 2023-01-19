package crouskiebackoffice.model.dao;

import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Collection;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.Picture;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.Tag;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe de gestion de données pour les produits.
 *
 * Implémente l'interface {@link DAO}.
 */
public class DAOProduct extends DAO<Product> {

    /**
     * Récupère la requête SQL permettant de récupérer toutes les données d'un
     * produit.
     *
     * @return la requête SQL
     */
    @Override
    protected String getRequestForAllData() {
        //set requet permet de récupérer les info d'un produit en faison la liasion avec
        //  la collection (id, name) ces valeurs seront null si il n'appartien au aucun collection
        //  les taille existante qui sont concaténer en une String sous la form  :  idsize, namesize;;;idsize2, namesize2
        //  les couleur existante qui sont concaténer en une String sous la form :  namecolor;;;namecolor2
        //On fait comme sa pour minimiser le nombre de requet.
        return """
              SELECT DISTINCT idprod, nameprod, descriptionprod, priceprod, idcollection, envente,
                                  case 
                                    when idcollection is null then null
                                    else namecollection
                                      end as namecollection,
                                  (SELECT group_concat(CONCAT(idsize, ',,,', namesize) SEPARATOR';;;') FROM product NATURAL JOIN existingsize NATURAL JOIN cloth_size WHERE P1.idprod = idprod) as size_existing,
                                  (SELECT group_concat(namecolor SEPARATOR';;;') FROM product NATURAL JOIN existingcolor WHERE P1.idprod = idprod) as color_existing,
                                  (SELECT group_concat(CONCAT(idtag, ',,,', nametag) SEPARATOR';;;') FROM product NATURAL JOIN tags_product NATURAL JOIN tag WHERE P1.idprod = idprod) as tags,
                                  (SELECT group_concat(CONCAT(pathpicture, ',,,', altpicture, ',,,', idprod) SEPARATOR';;;') FROM  picture WHERE P1.idprod = idprod ) as pictures 
                                  FROM `product` P1 NATURAL LEFT OUTER JOIN existingsize NATURAL LEFT OUTER JOIN existingcolor NATURAL LEFT OUTER JOIN `collection`""";
    }

    /**
     * Récupère le nom de la table contenant les produits dans la base de
     * données.
     *
     * @return le nom de la table
     */
    @Override
    protected String getTableName() {
        return "product";
    }

    /**
     * Crée un objet {@link Product} à partir des données contenues dans un
     * {@link HashMap}.
     *
     * @param obj les données sous forme de {@link HashMap}
     * @return l'objet {@link Product} créé
     */
    @Override
    protected Product parseData(HashMap<String, Object> obj) {
        List<ClothSize> size_existing = new LinkedList<>();
        List<Color> color_existing = new LinkedList<>();
        List<Tag> tags = new LinkedList<>();
        List<Picture> pictures = new LinkedList<>();

        String spliteurObject = ";;;";
        String spliteurAtribut = ",,,";

        if (obj.get("size_existing") != null) {
            for (String sizeLine : obj.get("size_existing").toString().split(spliteurObject)) {
                int id = Integer.parseInt(sizeLine.split(spliteurAtribut)[0]);
                String name = sizeLine.split(spliteurAtribut)[1];
                size_existing.add(new ClothSize(id, name));
            }
        }
        if (obj.get("color_existing") != null) {
            for (String name : obj.get("color_existing").toString().split(spliteurObject)) {
                color_existing.add(new Color(name));
            }
        }
        if (obj.get("tags") != null) {
            for (String sizeLine : obj.get("tags").toString().split(spliteurObject)) {
                int id = Integer.parseInt(sizeLine.split(spliteurAtribut)[0]);
                String name = sizeLine.split(spliteurAtribut)[1];
                tags.add(new Tag(id, name));
            }
        }
        if (obj.get("pictures") != null) {
            for (String picLine : obj.get("pictures").toString().split(spliteurObject)) {
                System.out.println(picLine);
                String path = picLine.split(spliteurAtribut)[0];
                String altPicture = picLine.split(spliteurAtribut)[1];
                Integer idProd = null;
                if (!picLine.split(spliteurAtribut)[2].isBlank()) {
                    idProd = Integer.parseInt(picLine.split(spliteurAtribut)[2]);
                }

                pictures.add(new Picture(path, altPicture, idProd));
            }
        }

        return new Product((int) obj.get("idprod"), obj.get("nameprod").toString(), obj.get("descriptionprod").toString(), (float) obj.get("priceprod"),
                (obj.get("idcollection") != null) ? new Collection((int) obj.get("idcollection"), obj.get("namecollection").toString()) : null,
                (boolean) obj.get("envente"),
                color_existing, size_existing, tags, pictures);
    }

    public Boolean setNameOf(Product product, String newName) throws SQLException, ErrorHandelabelAdapter {
        return setNameOf(product.getId(), newName);
    }

    public Boolean setNameOf(int idProduct, String newName) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {newName, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET nameprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setDescriptionOf(Product product, String newDescription) throws SQLException, ErrorHandelabelAdapter {
        return setDescriptionOf(product.getId(), newDescription);
    }

    public Boolean setDescriptionOf(int idProduct, String newDescription) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {newDescription, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET descriptionprod = ? WHERE idprod = ?", args) == 1;
    }

    public Boolean setPriceOf(Product product, Float newPrice) throws SQLException, ErrorHandelabelAdapter {
        return setPriceOf(product.getId(), newPrice);
    }

    public Boolean setPriceOf(int idProduct, Float newPrice) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {newPrice, idProduct};
        return super.execute("UPDATE " + getTableName() + " SET priceprod = ? WHERE idprod = ?", args) == 1;
    }

    /**
     * Insert ou met à jous le produit
     *
     * @param product le produit à inserer ou mettre à jour
     * @param forceInsert forcé l'insertion
     * @return si l'action à été faite avec succes
     * @throws SQLException s'il y a une erreur SQL
     * @throws ErrorHandelabelAdapter en cas d'erreur de connexion SQL
     */
    @Override
    public Boolean insertOrUpdate(Product product, boolean forceInsert) throws SQLException, ErrorHandelabelAdapter {
        startTransaction();
        setSavePoint("edit_product");
        boolean succes = false;
        if (exist(product) && !forceInsert) {
            Object[] idArg = {product.getId()};
            super.execute("DELETE FROM existingsize WHERE idprod = ?", idArg);
            super.execute("DELETE FROM tags_product WHERE idprod = ?", idArg);
            super.execute("DELETE FROM existingcolor WHERE idprod = ?", idArg);
            super.execute("DELETE FROM picture WHERE idprod = ?", idArg);

            Object[] args2 = {product.getName(), product.getDescription(), product.getPrice(),
                (product.getCollection() != null ? product.getCollection().getId() : null),
                product.isEnVente(),
                product.getId()
            };
            succes = super.execute("UPDATE " + getTableName() + " SET nameprod = ?, descriptionprod = ?, priceprod = ?, idcollection = ?, envente = ? WHERE idprod = ?", args2) == 1
                    && insertAll(product) && insertAllPictures(product);

        } else {

            Object[] args = {product.getName(), product.getDescription(), product.getPrice(),
                (product.getCollection() != null ? product.getCollection().getId() : null),
                product.isEnVente()};
            List<HashMap<String, Object>> generatedKeys = super.insert("INSERT INTO " + getTableName() + " (nameprod, descriptionprod, priceprod, idcollection, envente) VALUES (?, ?, ?, ?, ?)",
                    "idprod", args);
            if (generatedKeys != null) {
                BigInteger bi = (BigInteger) generatedKeys.get(0).get("generated_key");
                product.setId(bi.intValue());

                succes = insertAll(product);
                if (succes) {
                    succes = insertAllPictures(product);
                }
            } else {
                succes = false;
            }
        }
        if (!succes) {
            rollbackTo("edit_product");
        }
        endTransaction();
        return succes;
    }

    /**
     * Insert toutes les images liée au prduit dans la base de donnée
     *
     * @param product le produit en question
     * @return true si l'insertion c'est fait avec succes
     * @throws SQLException s'il y a une erreur SQL
     * @throws ErrorHandelabelAdapter en cas d'erreur de connexion SQL
     */
    private boolean insertAllPictures(Product product) throws SQLException, ErrorHandelabelAdapter {
        if (product.getPictures().isEmpty()) {
            return true;
        }
        Object[] args = new Object[product.getPictures().size() * 3];
        StringBuilder ptsInterogration = new StringBuilder();
        var it = product.getPictures().iterator();
        for (int i = 0; i < product.getPictures().size() && it.hasNext(); i++) {
            if (i == 0) {
                ptsInterogration.append("(?, ?, ?)");
            } else {
                ptsInterogration.append(", (?, ?, ?)");
            }
            Picture pic = it.next();
            int curr = 3 * i;
            args[curr] = pic.getPath();
            args[curr + 1] = pic.getIdProd();
            args[curr + 2] = pic.getAlt();
        }

        return super.execute("INSERT INTO picture (pathpicture, idprod, altpicture) VALUES " + ptsInterogration.toString(), args) != 0;
    }

    /**
     * Insert tout les tags, size et color liée à se produit
     *
     * @param product le produit en question
     * @return true en cas de succes
     * @throws SQLException s'il y a une erreur SQL
     * @throws ErrorHandelabelAdapter en cas d'erreur de connexion SQL
     */
    private Boolean insertAll(Product product) throws SQLException, ErrorHandelabelAdapter {
        List<Integer> idsTag = new LinkedList<>();

        System.out.println(Arrays.toString(product.getTags().toArray()));
        for (Tag tag : product.getTags()) {
            idsTag.add(tag.getId());
        }

        List<Integer> idsSize = new LinkedList<>();
        for (ClothSize clothSize : product.getExistingSize()) {
            idsSize.add(clothSize.getId());
        }

        return insertAll(product.getId(), "tags_product", "idtag", idsTag.toArray())
                && insertAll(product.getId(), "existingsize", "idsize", idsSize.toArray())
                && insertAll(product.getId(), "existingcolor", "namecolor", product.getExistingColor().toArray());
    }

    /**
     * Insert toutes les donnée indiquer par {@code valuesName} dans la table
     * {@code nameTable} attacher à l'id {@code id}
     *
     * @param id l'id du produit lié à ces donnée
     * @param nameTable le nom de la table
     * @param name le champs à inserer
     * @param valuesName le valeurs à inserer
     * @return true en cas de succes
     * @throws SQLException s'il y a une erreur SQL
     * @throws ErrorHandelabelAdapter en cas d'erreur de connexion SQL
     */
    public Boolean insertAll(int id, String nameTable, String name, Object[] valuesName) throws SQLException, ErrorHandelabelAdapter {
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
        boolean a = super.execute("INSERT INTO " + nameTable + " (" + name + ", idprod) values " + ptsInterogration.toString(), args) != 0;
        System.out.println(a);
        return a;
    }

    /**
     *
     * @param product
     * @return true si le produit exist dans la BD
     */
    @Override
    public Boolean exist(Product product) {
        return product != null && product.getId() != -1;
    }

    /**
     * Supprime le produit de la BD
     *
     * @param obj le produit
     * @return true si succes
     * @throws SQLException s'il y a une erreur SQL
     * @throws ErrorHandelabelAdapter en cas d'erreur de connexion SQL
     */
    @Override
    public Boolean remove(Product obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getId()};
        return super.execute("DELETE FROM " + getTableName() + " WHERE idprod = ? ", args) == 1;
    }

    public int getNextId() throws SQLException, ErrorHandelabelAdapter {
        System.out.println(super.selectAll("SHOW TABLE STATUS WHERE `Name` = 'product'; ", null).toString());
        System.out.println(super.selectAll("SHOW TABLE STATUS WHERE `Name` = 'product'; ", null).get(0).toString());
        System.out.println(super.selectAll("SHOW TABLE STATUS WHERE `Name` = 'product'; ", null).get(0).get("auto_increment").toString());
        return ((BigInteger) super.selectAll("SHOW TABLE STATUS WHERE `Name` = 'product'; ", null).get(0).get("auto_increment")).intValue();
    }
}
