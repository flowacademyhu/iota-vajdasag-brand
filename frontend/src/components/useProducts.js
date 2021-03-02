import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'

const useProducts = () => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])

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
    setUsers(listOfAllUsers?.sort((a, b) => sortColumn(a, b)))
  }, [])

  return { listOfAllUsers }
}

export default useProducts
