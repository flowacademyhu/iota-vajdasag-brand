import mockable from '../communications/mockingInstance'
var MockAdapter = require("axios-mock-adapter");


var mock = new MockAdapter(mockable);


mock.onGet("/events").reply(200, {
    users: [{ id: 1, name: "John Smith" }],
  });