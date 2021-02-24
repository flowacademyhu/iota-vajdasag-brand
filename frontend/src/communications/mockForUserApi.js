import api from './apiInstance'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(api)

mock.onGet('/api/users').reply(200, {
  users: [
    {
      id: 1,
      name: 'John',
      email: 'j@trade.com',
      isApproved: true,
      dateOfRegistration: 2020,
    },
    {
      id: 2,
      name: 'Karl',
      email: 'k@trade.com',
      isApproved: false,
      dateOfRegistration: 2021,
    },
    {
      id: 3,
      name: 'Alma',
      email: 'w@hun.ko',
      isApproved: true,
      dateOfRegistration: 1999,
    },
  ],
})

mock.onPost('/login', {
    email: 'teszt@teszt.com',
    password: '12345678',
  })
  .reply(200, '84848fhgvripuerh98r4gu9hg4ru9hrv')

  mock.onPost('/forgetpassword',{
    email: 'teszt@teszt.com',
  }).reply(200)

  mock.onPost('/forgetpassword',{
    email: 't@teszt.com',
  }).reply(400)

mock.onPost('/registration').reply(201)
mock.onPut(`/users/2/approval`).reply(200)
mock.onDelete('/users/1').reply(200)
mock.onDelete('/users/3').reply(201)
