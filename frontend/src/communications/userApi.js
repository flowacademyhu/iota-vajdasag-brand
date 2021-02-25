import api from './apiInstance'
//import './mockForUserApi'

export const getUsers = async () => {
  try {
    const response = await api.get('/getUsers')
    return response.data.users
  } catch (error) {
    throw new Error('Failed to get users.')
  }
}

export const login = (value) => {
  return api.post('/login', value)
}

export const forgottenpassword = async (value) => {
  try {
    console.log('value', value)
    const response = await api.post('/forgottenpassword', value)
    return response
  } catch (e) {
    console.log(e)
    if (e.response.status === 400) throw new Error('no user')
    if (e.response.status === 404 || e.response.status === 500)
      throw new Error('no server')
  }
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
