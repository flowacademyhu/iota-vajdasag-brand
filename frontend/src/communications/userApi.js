import api from './apiInstance'
//import './mockForUserApi'

export const getUsers = async () => {
  try {
    const response = await api.get('/users')
    console.log('response', response.data)
    return response.data.content
  } catch (error) {
    throw new Error('Failed to get users.')
  }
}

export const login = (value) => {
  console.log('value api', value)
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
  const response = await api.delete(`/products/${id}`)
  if (response.status !== 200) {
    throw new Error('The deletion was unsuccessful.')
  }
}

export const updateProductData = async (value) => {
  const response = await api.put(`/items/${value.id}`, value)
  if (response.status !== 200) {
    throw new Error('The update was unsuccessful.')
  }
}

export const createProduct = (value) => {
  api.post("/items", value)
    .then((response) => {
      console.log('response', response)
    }).catch((err) => {
      if(err.status==="400")throw new Error("bad request")
      console.log(err)
    });
}

