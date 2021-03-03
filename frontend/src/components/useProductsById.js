import { useState, useEffect } from 'react'
import { getProductsByOwnerId } from '../communications/userApi'

const useProductsForCompany = (ownerId) => {
  const [companysProducts, setCompanysProducts] = useState([])

  const fetchUserProducts = async (ownerId) => {
    const fetchedUserProducts = await getProductsByOwnerId(ownerId)
    setCompanysProducts(fetchedUserProducts)
  }

  useEffect(() => {
    fetchUserProducts(ownerId)
  }, [ownerId])

  return companysProducts
}

export default useProductsForCompany
