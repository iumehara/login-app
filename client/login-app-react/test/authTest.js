import expect from 'expect'
import React from 'react'
import auth from '../src/js/auth'
import localStorage from 'localStorage'

describe('auth', () => {
  describe('login', () => {
    beforeEach(() => {
      delete localStorage.token
    })

    afterEach(() => expect.restoreSpies())

    it('redirects with existing token if one exists', () => {
      let redirectCallbackSpy = expect.createSpy()
      let onChangeSpy = expect.spyOn(auth, 'onChange')
      let initialToken = 'initialToken'
      localStorage.token = initialToken

      auth.login('adam', 'secreta', redirectCallbackSpy)

      expect(localStorage.token).toBe(initialToken)
      expect(redirectCallbackSpy).toHaveBeenCalledWith(true)
      expect(onChangeSpy).toHaveBeenCalledWith(true)
    })

    it('redirects with correct params', () => {
      let redirectCallbackSpy = expect.createSpy()
      let onChangeSpy = expect.spyOn(auth, 'onChange')

      auth.login('adam', 'secreta', redirectCallbackSpy)

      expect(localStorage.token).toExist()
      expect(redirectCallbackSpy).toHaveBeenCalledWith(true)
      expect(onChangeSpy).toHaveBeenCalledWith(true)
    })

    it('does no redirect if params dont match', () => {
      let redirectCallbackSpy = expect.createSpy()
      let onChangeSpy = expect.spyOn(auth, 'onChange')

      auth.login('wrong name', 'wrong password', redirectCallbackSpy)

      expect(localStorage.token).toNotExist()
      expect(redirectCallbackSpy).toHaveBeenCalledWith(false)
      expect(onChangeSpy).toHaveBeenCalledWith(false)
    })
  })

  describe('logout', () => {
    it('deletes existing token and updates state', () => {
      let onChangeSpy = expect.spyOn(auth, 'onChange')
      let initialToken = 'initialToken'
      localStorage.token = initialToken

      auth.logout()

      expect(localStorage.token).toNotExist()
      expect(onChangeSpy).toHaveBeenCalledWith(false)
    })
  })

  describe('loggedIn', () => {
    it('returns false if token doesnt exist', () => {
      let isLoggedIn = auth.loggedIn()

      expect(isLoggedIn).toBe(false)
    })

    it('returns true if token exists', () => {
      let initialToken = 'initialToken'
      localStorage.token = initialToken

      let isLoggedIn = auth.loggedIn()

      expect(isLoggedIn).toBe(true)
    })
  })
})
