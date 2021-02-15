import React from "react";
import ListElement from "./listOfProducts/ListElement";
import { useTranslation } from "react-i18next";
import useProducts from "./useProducts";

const ProductList = () => {
  const { products } = useProducts();
  const { t } = useTranslation();

  return (
      <table className="table table-striped">
        <thead>
          <tr>
            <th scope="col">{t("title")}</th>
            <th scope="col">{t("address")}</th>
            <th scope="col">{t("city")}</th>
            <th scope="col">{t("category")}</th>
          </tr>
        </thead>
        <tbody>
          {products?.map((product) => (
            <ListElement product={product} key={product.id} />
          ))}
        </tbody>
      </table>
  );
};

export default ProductList;
