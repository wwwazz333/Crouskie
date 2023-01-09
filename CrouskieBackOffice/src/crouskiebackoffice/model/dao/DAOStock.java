package crouskiebackoffice.model.dao;

import crouskiebackoffice.controle.ErrorHandeler;
import crouskiebackoffice.exceptions.ErrorHandelabelAdapter;
import crouskiebackoffice.model.ClothSize;
import crouskiebackoffice.model.Color;
import crouskiebackoffice.model.Product;
import crouskiebackoffice.model.ProductColorSize;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * La classe {@code DAOStock} est une classe qui permet de gérer les opérations
 * de base de données
 *
 * pour l'objet {@link ProductColorSize}.
 */
public class DAOStock extends DAO<ProductColorSize> {

    /**
     * Retourne le nom de la table en base de données associée à l'objet
     * {@link ProductColorSize}.
     *
     * @return le nom de la table en base de données.
     */
    @Override
    protected String getTableName() {
        return "stocked natural join product natural join color natural join cloth_size";
    }

    /**
     * Retourne la requête SQL permettant de récupérer toutes les données de la
     * table associée à l'objet {@link ProductColorSize} en base de données.
     *
     * @return la requête SQL permettant de récupérer toutes les données de la
     * table.
     */
    @Override
    protected String getRequestForAllData() {
        return "SELECT * FROM product NATURAL JOIN existingcolor NATURAL JOIN color NATURAL JOIN existingsize NATURAL JOIN cloth_size NATURAL LEFT OUTER JOIN stocked";
    }

    /**
     * Insère ou met à jour un objet {@link ProductColorSize} en base de
     * données. Si l'objet existe
     *
     * déjà en base de données, alors sa quantité est mise à jour. Sinon,
     * l'objet est inséré en base
     *
     * de données.
     *
     * @param productColorSize l'objet {@link ProductColorSize} à insérer ou
     * mettre à jour.
     *
     * @return {@code true} si l'opération a réussi, {@code false} sinon.
     *
     * @throws SQLException lorsqu'une erreur SQL est survenue lors de
     * l'opération.
     *
     * @throws ErrorHandelabelAdapter lorsqu'une erreur est survenue lors de
     * l'opération.
     */
    @Override
    public Boolean insertOrUpdate(ProductColorSize productColorSize) throws SQLException, ErrorHandelabelAdapter {
        if (exist(productColorSize)) {
            Object[] args = {productColorSize.getQuantity(), productColorSize.getProduct().getId(), productColorSize.getColor().getName(), productColorSize.getSize().getId()};

            return super.execute("UPDATE stocked SET quantitystocked = ? WHERE idprod = ? and namecolor = ? and idsize = ?", args) == 1;

        } else {
            Object[] args = {productColorSize.getProduct().getId(), productColorSize.getColor().getName(), productColorSize.getSize().getId(), productColorSize.getQuantity()};
            return super.execute("INSERT INTO stocked (idprod, namecolor, idsize, quantitystocked) VALUES (?, ?, ?, ?)", args) == 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ProductColorSize parseData(HashMap<String, Object> obj) {
        return new ProductColorSize(
                new Product((int) obj.get("idprod"), obj.get("nameprod").toString(), obj.get("descriptionprod").toString(), (float) obj.get("priceprod"), null),
                new Color(obj.get("namecolor").toString()),
                new ClothSize((int) obj.get("idsize"), obj.get("namesize").toString()), (Integer) obj.get("quantitystocked"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean exist(ProductColorSize obj) {
        if ((obj.getColor() == null || obj.getSize() == null) || obj.getProduct() == null) {
            return false;
        }

        return ErrorHandeler.getInstance().exec(() -> {
            Long count = (Long) super.selectAll("SELECT count(*) as cnt FROM stocked WHERE idprod = ? and namecolor = ? and idsize = ?",
                    new Object[]{obj.getProduct().getId(), obj.getColor().getName(), obj.getSize().getId()}).get(0).get("cnt");
            return count != null && count == 1;
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean remove(ProductColorSize obj) throws SQLException, ErrorHandelabelAdapter {
        Object[] args = {obj.getProduct().getId(), obj.getColor().getName(), obj.getSize().getId()};
        return super.execute("DELETE FROM " + "stocked" + " WHERE idpp = ? and namecolor = ? and idsize = ? ", args) == 1;
    }
}
