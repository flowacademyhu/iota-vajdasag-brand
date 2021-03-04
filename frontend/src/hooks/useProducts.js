import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'
import { normalize, highlightableProps } from '../textHelpers'
import { sortColumn } from '../sortHelpers'

const useProducts = (searchKeyword) => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])
  const [products, setProducts] = useState([])
  const [sortKey, setSortKey] = useState('')
  const [isSortAscending, setAscendingSort] = useState(true)

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

  return {
    products,
    sortKey,
    setSortKey,
    isSortAscending,
    setAscendingSort,
  }
}

export default useProducts
