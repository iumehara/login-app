import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import auth from '../src/js/auth'
import HomeComponent from '../src/js/HomeComponent'
import UserDetailComponent from '../src/js/UserDetailComponent'

describe('HomeComponent page', () => {
  beforeEach(() => auth.deleteSession())
  afterEach(() => auth.deleteSession())

  describe('not logged in', () => {
    it('displays the component', () => {
      const homeComponent = shallow(<HomeComponent/>)

      expect(homeComponent.contains(<h1>Home</h1>)).toBe(true)
    })

    it('displays login message if not logged in', () => {
      let homeComponent = shallow(<HomeComponent/>)

      expect(homeComponent.find(UserDetailComponent).length).toBe(0)
    })
  })

  describe('logged in', () => {
    it('displays user info if logged in', () => {
      auth.setSession({username: 'adam', token: 'token'})

      let homeComponent = shallow(<HomeComponent/>)

      expect(homeComponent.find(UserDetailComponent).length).toBe(1)
    })
  })
})
