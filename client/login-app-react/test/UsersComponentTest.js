import expect from 'expect'
import { shallow, mount } from 'enzyme'
import React from 'react'
import UsersComponent from '../src/js/UsersComponent'
import UsersTable from '../src/js/UsersTable'

describe('UsersComponent', () => {
  it('displays a UsersTable', () => {
    let stubGetUsersData = () => {
      return {
        then: (setStateImplementation) => {
          setStateImplementation([
            {id: 1, username: 'adam', role: 'staff'},
            {id: 2, username: 'bob', role: 'staff'},
            {id: 3, username: 'cam', role: 'admin'}
          ])
        }
      }
    }

    let spy = expect.spyOn(
      UsersComponent.prototype, 'getUsers'
    ).andReturn(
      stubGetUsersData()
    )

    const usersComponent = shallow(<UsersComponent/>)

    expect(spy).toHaveBeenCalled()
    expect(usersComponent.find(UsersTable).length).toBe(1)

    spy.restore()
  })
})
