import { useState, useEffect } from 'react'
import { getProductsByUserId } from '../communications/userApi'

const useProductsForCompany = () => {
  const [companysProducts, setCompanysProducts] = useState([])

  const fetchUserProducts = async () => {
    const fetchedUserProducts = await getProductsByUserId(1)
    setCompanysProducts(fetchedUserProducts)
  }

  useEffect(() => {
    fetchUserProducts()
  }, [])

  return { companysProducts }
}

export default useProductsForCompany
