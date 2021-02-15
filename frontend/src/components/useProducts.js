import { useState, useEffect } from "react";
import { getProducts } from "../communications/productApi";

const useProducts = () => {
  const [products, setProducts] = useState([]);

  const fetchProducts = async () => {
    const fetchedProducts = await getProducts();
    setProducts(fetchedProducts);
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  return { products };
};

export default useProducts;
