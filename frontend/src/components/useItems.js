import { useState, useEffect } from 'react'
import { getAllItems, getItemsByUserId } from '../communications/userApi'

export const GetItems = () => {
  const [listOfAllItems, setListOfAllItems] = useState([])

  const fetchItems = async () => {
    const fetchedItems = await getAllItems()
    console.log(fetchedItems)
    setListOfAllItems(fetchedItems)
  }

  useEffect(() => {
    fetchItems()
  }, [])

  return { fetchItems, listOfAllItems }
}

export const GetItemsById = () => {
  const [companysItems, setCompanysItems] = useState([])

  const fetchUserItems = async () => {
    const fetchedUserItems = await getItemsByUserId(1)
    setCompanysItems(fetchedUserItems)
  }

  useEffect(() => {
    fetchUserItems()
  }, [])

  return { companysItems }
}
