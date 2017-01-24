import auth from './auth'

export const getUsers = (token) => {
  return get(token, 'http://localhost:8080/users')
}

export const getUser = (token, username) => {
  return get(token, `http://localhost:8080/users/${username}`)
}

export const postLogin = (body) => {
  return post({}, 'http://localhost:8080/login', body)
}

const get = (token, path) => {
  return fetch(path, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
  }).then((response) => {
    return response.json()
  })
}

const post = (token, path, body) => {
  return fetch(path, {
    headers: {
      'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify(body)
  }).then((response) => {
    return response.json()
  })
}
