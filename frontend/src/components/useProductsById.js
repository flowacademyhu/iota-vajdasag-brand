import { useState, useEffect } from 'react'
import { getProductsByOwnerId } from '../communications/userApi'
import { normalize, highlightableProps } from '../textHelpers'
import { sortColumn } from '../sortHelpers'

const useProductsForCompany = (ownerId, searchKeyword) => {
  const [companysProducts, setCompanysProducts] = useState([])
  const [products, setProducts] = useState([])
  const [sortKey, setSortKey] = useState('')
  const [isSortAscending, setAscendingSort] = useState(true)

  const fetchUserProducts = async (ownerId) => {
    const fetchedUserProducts = await getProductsByOwnerId(ownerId)
    setCompanysProducts(fetchedUserProducts)
  }

  useEffect(() => {
    fetchUserProducts(ownerId)
  }, [ownerId])

  useEffect(() => {
    const searchWord = normalize(searchKeyword)
    setProducts(
      companysProducts
        ?.sort((a, b) => sortColumn(a, b, sortKey, isSortAscending))
        .filter((product) =>
          Object.entries(product).filter(
            ([key, value]) =>
              highlightableProps.includes(key) && value.includes(searchWord)
          )
        )
    )
  }, [searchKeyword, companysProducts, sortKey, isSortAscending])

  return {
    products,
    sortKey,
    setSortKey,
    isSortAscending,
    setAscendingSort,
  }
}

export default useProductsForCompany
