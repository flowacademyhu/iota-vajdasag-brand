import api from './apiInstance'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(api)

mock
  .onPost('/forgottenpassword', {
    email: 'teszt@teszt.com',
  })
  .reply(200)

mock
  .onPost('/forgottenpassword', {
    email: 't@teszt.com',
  })
  .reply(400)

mock.onPut(`/users/2/approval`).reply(200)
mock.onDelete('/users/1').reply(200)
mock.onDelete('/users/3').reply(201)

mock.onDelete('/products/1').reply(200)
mock.onDelete('/products/2').reply(404)
