import axios from "axios";
import MockAdapter from "axios-mock-adapter";

export const loginMock = () => {
    const mock = new MockAdapter(axios)
    mock.onPost("http://localhost:3000/api", { email: "teszt@teszt.com", password: "1234567" })
        .reply(200, "84848fhgvripuerh98r4gu9hg4ru9hrv")
}