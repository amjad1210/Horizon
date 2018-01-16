import React, { PureComponent } from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'

import { Sidebar, Loader, Segment, Header, Menu } from 'semantic-ui-react'

import Window from '../Window/Window'

import * as NetworkActions from '../../actions/NetworkActions'

class NetworkWindow extends PureComponent {
  componentDidMount() {
    this.props.actions.fetchPorts()
  }

  render() {
    const { isLoading, payload, error } = this.props

    return (
      <Window title='Network' width={500} height={300}>
        <Sidebar.Pushable>
          <Sidebar as={Menu} animation='overlay' direction='top' visible={isLoading} inverted>
            <Loader as={Menu.Item}>Loading</Loader>
          </Sidebar>
          <Sidebar.Pusher>
            <Segment basic>
              <Header as='h3'>Application Content</Header>
            </Segment>
          </Sidebar.Pusher>
        </Sidebar.Pushable>
      </Window>
    )
  }
}

const mapStateToProps = state => ({
  isLoading: state.network.isLoading,
  payload: state.network.payload,
  error: state.network.error
})

const mapDispatchToProps = dispatch => ({
  actions: bindActionCreators(NetworkActions, dispatch)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NetworkWindow)
