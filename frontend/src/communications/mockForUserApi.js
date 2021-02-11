import axios from "axios";
import MockAdapter from "axios-mock-adapter";

const mock = new MockAdapter(axios);

mock.onGet("/users").reply(200, {
  users: [
    {
      id: 1,
      name: "John",
      email: "j@trade.com",
      approvedUser: true,
      dateOfRegistration: 2020,
    },
    {
      id: 2,
      name: "Karl",
      email: "k@trade.com",
      approvedUser: false,
      dateOfRegistration: 2021,
    },
    {
      id: 3,
      name: "Alma",
      email: "w@hun.ko",
      approvedUser: false,
      dateOfRegistration: 1999,
    },
  ],
});
