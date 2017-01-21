import expect from 'expect'
import React from 'react'
import { shallow } from 'enzyme'
import NewUserComponent from '../../src/js/users/NewUserComponent'

describe('NewUserComponent', () => {
  it('displays form', () => {
      const newUserComponent = shallow(<NewUserComponent/>)

      expect(newUserComponent.node).toInclude(<h1>new user</h1>)
  })
})
