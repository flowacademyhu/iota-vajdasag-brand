import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import { normalize, highlightableProps } from '../textHelpers'
import { sortColumn } from '../sortHelpers'

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

  useEffect(() => {
    console.log('sortkey', sortKey, 'ascending', isSortAscending)
    const searchWord = normalize(searchKeyword)
    setProducts(
      listOfAllProducts
        ?.sort((a, b) => sortColumn(a, b, sortKey, isSortAscending))
        .filter((product) =>
          Object.entries(product).filter(
            ([key, value]) =>
              highlightableProps.includes(key) && value.includes(searchWord)
          )
        )
    )
  }, [searchKeyword, listOfAllProducts, sortKey, isSortAscending])

  return { products }
}

export default useProducts
