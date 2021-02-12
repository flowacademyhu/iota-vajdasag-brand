import axios from "axios";
import MockAdapter from "axios-mock-adapter";

const mock = new MockAdapter(axios);

mock.onGet("/users").reply(200, {
  users: [
    {
      id: 1,
      name: "John",
      email: "j@trade.com",
      isApproved: true,
      dateOfRegistration: 2020,
    },
    {
      id: 2,
      name: "Karl",
      email: "k@trade.com",
      isApproved: false,
      dateOfRegistration: 2021,
    },
    {
      id: 3,
      name: "Alma",
      email: "w@hun.ko",
      isApproved: false,
      dateOfRegistration: 1999,
    },
  ],
});
export const loginMock = () => {
  const mock = new MockAdapter(axios);
  mock
    .onPost("http://localhost:3000/api/login", {
      email: "teszt@teszt.com",
      password: "12345678",
    })
    .reply(200, "84848fhgvripuerh98r4gu9hg4ru9hrv");
};

export const signUpMock = () => {
  const mock = new MockAdapter(axios);
  console.log("singup mock");
  mock.onPost("http://localhost:3000/api/registration").reply(201);
};
