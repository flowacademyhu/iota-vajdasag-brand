import api from './apiInstance'
//import './mockForUserApi'


api.interceptors.request.use(x => {
  console.log("header: " + JSON.stringify(x.headers))
  console.log("data: " + JSON.stringify(x.data))
  return x
})
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

export const signUp = async (value, type) => {
  const data = {
    "full_name": value.name,
    "tax_number": value.taxNumber,
    "address": value.address,
    "email": value.email,
    "type":type,
    "password":value.password
  }
  console.log("data: "+JSON.stringify(data))
  return await api.post('/registration', data)
}

export const sendApproval = async (userId) => {
  try {
    return await api.put(`/users/${userId}/approval`)
  } catch (error) {
    throw new Error('Registration approval miscarried.')
  }
}
