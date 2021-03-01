import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'

/*
 * Removes all accents from words and makes them uppercase.
 **/
const makeWordComparable = (keyword) => {
  return keyword
    ?.normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '')
    .toUpperCase()
}

const useProducts = (searchKeyword) => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])
  const [products, setProducts] = useState([])

  const getAllProducts = async () => {
    const fetchedProducts = await fetchProducts()
    setListOfAllProducts(fetchedProducts)
  }

  useEffect(() => {
    getAllProducts()
  }, [])

  useEffect(() => {
    const searchWord = makeWordComparable(searchKeyword)
    setProducts(
      listOfAllProducts.filter(
        (product) =>
          makeWordComparable(product.name).includes(searchWord) ||
          makeWordComparable(product.address).includes(searchWord) ||
          makeWordComparable(product.city).includes(searchWord) ||
          makeWordComparable(product.category).includes(searchWord) ||
          makeWordComparable(product.owner).includes(searchWord)
      )
    )
  }, [searchKeyword, listOfAllProducts])

  return { products }
}

export default useProducts
