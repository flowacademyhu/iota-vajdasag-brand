import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import { removeAccentsFromWords } from '../components/frequentlyUsedFunctions'

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
    const searchWord = removeAccentsFromWords(searchKeyword)
    setProducts(
      listOfAllProducts.filter(
        (product) =>
          removeAccentsFromWords(product.name).includes(searchWord) ||
          removeAccentsFromWords(product.address).includes(searchWord) ||
          removeAccentsFromWords(product.city).includes(searchWord) ||
          removeAccentsFromWords(product.category).includes(searchWord) ||
          removeAccentsFromWords(product.ownerName).includes(searchWord)
      )
    )
  }, [searchKeyword, listOfAllProducts])

  return { products }
}

export default useProducts
