import expect from 'expect'
import { shallow } from 'enzyme'
import React from 'react'
import localStorage from 'localStorage'
import HomeComponent from '../src/js/HomeComponent'
import UserDetailComponent from '../src/js/UserDetailComponent'

describe('HomeComponent page', () => {
  beforeEach(() => delete localStorage.token)
  afterEach(() => delete localStorage.token)

  it('displays the component', () => {
    const homeComponent = shallow(<HomeComponent/>)

    expect(homeComponent.contains(<h1>Home</h1>)).toBe(true)
  })

  it('displays user info if logged in', () => {
    localStorage.token = 'token'

    let homeComponent = shallow(<HomeComponent/>)

    expect(homeComponent.find(UserDetailComponent).length).toBe(1)
  })

  it('displays login message if not logged in', () => {
    delete localStorage.token

    let homeComponent = shallow(<HomeComponent/>)

    expect(homeComponent.find(UserDetailComponent).length).toBe(0)
  })
})
