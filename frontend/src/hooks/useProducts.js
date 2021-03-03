import { useState, useEffect, useCallback } from 'react'
import { fetchProducts } from '../communications/userApi'
import { normalize, highlightableProps } from '../textHelpers'

const useProducts = (sortKey, isSortAscending, searchKeyword) => {
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
    console.log('sortkey', sortKey, 'ascending', isSortAscending)
    const searchWord = normalize(searchKeyword)
    setProducts(
      listOfAllProducts
        ?.sort((a, b) => sortColumn(a, b))
        .filter((product) =>
          Object.entries(product).filter(
            ([key, value]) =>
              highlightableProps.includes(key) && value.includes(searchWord)
          )
        )
    )
  }, [searchKeyword, listOfAllProducts, sortKey, sortColumn, isSortAscending])

  return { products }
}

export default useProducts
