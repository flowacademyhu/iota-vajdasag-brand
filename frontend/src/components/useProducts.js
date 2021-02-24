import { useState, useEffect } from 'react'
import { getAllProducts, getProductsByUserId } from '../communications/userApi'

export const GetProducts = () => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])

  const fetchProducts = async () => {
    const fetchedProducts = await getAllProducts()
    console.log(fetchedProducts)
    setListOfAllProducts(fetchedProducts)
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
