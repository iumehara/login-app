import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import Login from '../src/js/Login'
import auth from '../src/js/auth'

describe('Login', () => {
  it('renders form', () => {
    let login = shallow(<Login/>)

    let form = login.find('form')

    expect(form.length).toBe(1)
    expect(form.find('input.username').length).toBe(1)
    expect(form.find('input.password').length).toBe(1)
    expect(form.find('button').length).toBe(1)
    expect(form.node.props.onSubmit).toExist()
  })

  it('handleSubmit', () => {
    let loginSpy = expect.spyOn(auth, 'login')
    let redirectIfLoggedInSpy = expect.spyOn(Login.prototype, 'redirectIfLoggedIn')
    let login = shallow(<Login/>)
    login.instance().refs = {
      username: { value: 'username'},
      password: { value: 'password'}
    }

    login.instance().handleSubmit({ preventDefault: () => {}})

    expect(loginSpy).toHaveBeenCalledWith(
      'username',
      'password',
      redirectIfLoggedInSpy
    )
    expect.restoreSpies()
  })
})
