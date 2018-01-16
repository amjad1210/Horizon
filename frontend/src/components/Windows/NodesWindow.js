import React, { PureComponent } from 'react'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'

import { Dimmer, Loader } from 'semantic-ui-react'
import ReactTable from "react-table";

import Window from '../Window/Window'

import * as NodeActions from '../../actions/NodeActions'

import 'react-table/react-table.css'

class NodesWindow extends PureComponent {
  componentDidMount() {
    this.props.actions.fetchNodes()
  }

  render() {
    const { isLoading, payload, error } = this.props

    const columns = [{
        accessor: 'online',
        width: 35
      }, {
        Header: 'Lan',
        accessor: 'lan'
      }, {
        Header: 'Hostname',
        accessor: 'hostname'
      }, {
        Header: 'User',
        accessor: 'user'
      }, {
        Header: 'Operating System',
        accessor: 'os'
      }, {
        expander: true,
        width: 35,
        Expander: ({ isExpanded, ...rest }) =>
          <div>
            {isExpanded
              ? <span>&#x229g9;</span>
              : <span>&#x2295;</span>}
          </div>,
        style: {
          cursor: "pointer",
          fontSize: 25,
          padding: "0",
          textAlign: "center",
          userSelect: "none"
        }
    }]

    return (
      <Window title='Nodes' width={500} height={300}>
        <Dimmer active={isLoading}>
          <Loader>Loading</Loader>
        </Dimmer>
        <ReactTable
          columns={columns}
          defaultPageSize={20}
          style={{
            height: "400px" // This will force the table body to overflow and scroll, since there is not enough room
          }}
          className="-highlight"
          resizeHandleStyles
          SubComponent={() => <div style={{padding: '10px'}}>Hello</div>}
        />
      </Window>
    )
  }
}

const mapStateToProps = state => ({
  isLoading: state.nodes.isLoading,
  payload: state.nodes.payload,
  error: state.nodes.error
})

const mapDispatchToProps = dispatch => ({
  actions: bindActionCreators(NodeActions, dispatch)
})

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NodesWindow)
