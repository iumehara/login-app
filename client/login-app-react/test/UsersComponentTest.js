import expect from 'expect'
import { shallow, mount } from 'enzyme'
import React from 'react'
import UsersComponent from '../src/js/UsersComponent'
import UsersTable from '../src/js/UsersTable'

describe('UsersComponent', () => {
  it('displays the component', () => {
    const usersComponent = shallow(<UsersComponent/>)

    expect(usersComponent.find(UsersTable)).toExist()
  })
})
