export const get = (resource) => {
  return fetch(pathForResource(resource), {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer token'
      }
  }).then((response) => {
    return response.json()
  })
}

const pathForResource = (resource) => {
    return 'http://localhost:8080/users'
}
