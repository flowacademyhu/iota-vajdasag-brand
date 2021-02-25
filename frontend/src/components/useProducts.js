import { useState, useEffect } from 'react'
import { getAllProducts, getProductsByUserId } from '../communications/userApi'

export const GetProducts = () => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])

  const fetchProducts = async () => {
    try {
      const fetchedProducts = await getAllProducts()
      setListOfAllProducts(fetchedProducts)
    } catch (error) {
      throw new Error('Failed to get products.')
    }
  }

  useEffect(() => {
    fetchProducts()
  }, [])

  return { listOfAllProducts }
}

export const GetProductsById = () => {
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
