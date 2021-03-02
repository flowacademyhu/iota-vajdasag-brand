import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import {
  removeAccentsFromWords,
  highlightTableProps,
} from '../components/frequentlyUsedFunctionsAndVariables'

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
      listOfAllProducts.filter((product) =>
        Object.entries(product).filter(
          ([key, value]) =>
            highlightTableProps.includes(key) && value.includes(searchWord)
        )
      )
    )
  }, [searchKeyword, listOfAllProducts])

  return { products }
}

export default useProducts
