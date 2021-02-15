import axios from "axios";
import MockAdapter from "axios-mock-adapter";

const mock = new MockAdapter(axios);

mock.onGet("http://localhost:3000/api/products").reply(200, {
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
});

mock
  .onPost("http://localhost:3000/api", {
    email: "teszt@teszt.com",
    password: "12345678",
  })
  .reply(200, "84848fhgvripuerh98r4gu9hg4ru9hrv");
