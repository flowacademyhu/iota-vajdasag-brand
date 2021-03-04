import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import { normalize, highlightableProps } from '../textHelpers'

const useProducts = (searchKeyword) => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])
  const [products, setProducts] = useState([])

  const getAllProducts = async () => {
    const fetchedProducts = await fetchProducts()
    console.log(fetchedProducts)
    setListOfAllProducts(fetchedProducts)
  }

  useEffect(() => {
    getAllProducts()
  }, [])

  useEffect(() => {
    const searchWord = normalize(searchKeyword)
    setProducts(
      listOfAllProducts.filter((product) =>
        Object.entries(product).filter(
          ([key, value]) =>
            highlightableProps.includes(key) && value.includes(searchWord)
        )
      )
    )
  }, [searchKeyword, listOfAllProducts])

  return { products }
}

export default useProducts
