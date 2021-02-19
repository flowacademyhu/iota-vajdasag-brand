import api from './apiInstance'
import MockAdapter from 'axios-mock-adapter'

const mock = new MockAdapter(api)

mock.onGet('/users').reply(200, {
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
      isApproved: false,
      dateOfRegistration: 1999,
    },
  ],
})

mock.onGet('/products').reply(200, {
  products: [
    {
      id: 1,
      title: "Hami Étterem",
      address: "Zenta, Egyik utca 2.",
      city: "Zenta",
      category: "gastronomy",
    },
    {
      id: 2,
      title: "Pihenő Szálló",
      address: "Zenta, Másik utca 4.",
      city: "Zenta",
      category: "accommodation",
    },
    {
      id: 3,
      title: "Városnéző körút",
      address: "Zenta, Fő tér 2.",
      city: "Zenta",
      category: "freeTime",
    },
  ],
})


mock
  .onPost('/login', {
    email: 'teszt@teszt.com',
    password: '12345678',
  })
  .reply(200, '84848fhgvripuerh98r4gu9hg4ru9hrv')

mock.onPost('/registration').reply(201)
mock.onPut(`/users/2/approval`).reply(200)
