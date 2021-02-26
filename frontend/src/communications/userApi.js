import api from './apiInstance'

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

export const updateProductData = async (productId, updatedProduct) => {
  const response = await api.put(`/items/${productId}`, updatedProduct)
  if (response.status !== 200) {
    throw new Error('The update was unsuccessful.')
  }
}

export const fetchOneProduct = async (productId) => {
  try {
    return await api.get(`/items/${productId}`)
  } catch (error) {
    throw new Error('The GET product request was unsuccessful.')
  }
}

export const fetchProducts = async () => {
  try {
    const response = await api.get('/items')
    return response.data
  } catch (e) {
    throw new Error('Error when fetching products from API.')
  }
}
