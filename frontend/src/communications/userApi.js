import api from './apiInstance'
import './mockForUserApi'

export const getUsers = async () => {
  try {
    const response = await api.get('/users')
    return response.data.users
  } catch (error) {
    throw new Error('Failed to get users.')
  }
}

export const login = (value) => {
  return api.post('/login', value)
}

export const signUp = async (value, type) => {
  return await api.post('/registration', {
    full_name: value.name,
    tax_number: value.taxNumber,
    address: value.address,
    email: value.email,
    type: type,
    password: value.password,
  })
}

export const sendApproval = async (userId) => {
  try {
    return await api.put(`/users/${userId}/approval`)
  } catch (error) {
    throw new Error('Registration approval miscarried.')
  }
}

export const getAllProducts = async () => {
  try {
    const response = await api.get('/items')
    return response.data.products
  } catch (error) {
    throw new Error('Failed to get products.')
  }
}

export const getProductsByUserId = async (userId) => {
  try {
    const response = await api.get(`/items/${userId}`)

    return response.data.products
  } catch (error) {
    throw new Error('Failed to get products.')
  }
}

export const deleteUserRegistration = async (userId) => {
  const response = await api.delete(`/users/${userId}`)
  if (response.status !== 200) {
    throw new Error('The deletion was unsuccessful.')
  }
}

export const deleteProduct = async (id) => {
  const response = await api.delete(`/items/${id}`)
  if (response.status !== 200) {
    throw new Error('The deletion was unsuccessful.')
  }
}
