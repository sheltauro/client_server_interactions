# Client Server interactions
The RPC module allows you to execute any function on a remote server.
The result is returned as an AsyncResponse which is similar to a future.


## Task Notes
- [x] Compile and run java code locally.
- [x] Implement RPC using TCP.
  - [x] Implement TCP using protos to pass objects.
  - [x] Implement method reflection.
- [x] Handle multithreading.
- [x] Return all requests with the same (status + result) response.
  - [x] Client should be able to read the status + result.
- [x] Create a future/callback function.
- [x] Add more abstractions for the user.
- [ ] Create V0 documentation.


## Ideas to improve this
- [ ] If the server is overloaded, buffer requests on the client itself.
- [ ] Create an RPC client/server stub for more clarity.
- [ ] Send heartbeats from client to server, to check if the server is up.

