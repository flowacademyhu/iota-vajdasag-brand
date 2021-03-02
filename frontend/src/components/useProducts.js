import { useState, useEffect, useCallback } from 'react'
import { fetchProducts } from '../communications/userApi'

const useProducts = ({ sortKey, isSortAscending }) => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])
  const [products, setProducts] = useState([])

  const getAllProducts = async () => {
    const fetchedProducts = await fetchProducts()
    setListOfAllProducts(fetchedProducts)
  }

  useEffect(() => {
    getAllProducts()
  }, [])

  const sortColumn = useCallback(
    (a, b) => {
      if (sortKey === '') {
        return 0
      }

      if (isSortAscending) {
        return a[sortKey] > b[sortKey] ? 1 : -1
      } else {
        return a[sortKey] < b[sortKey] ? 1 : -1
      }
    },
    [sortKey, isSortAscending]
  )

  useEffect(() => {
    setProducts(listOfAllProducts?.sort((a, b) => sortColumn(a, b)))
  }, [listOfAllProducts, sortKey, sortColumn])

  return { products }
}

export default useProducts
