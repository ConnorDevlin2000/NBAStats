import React, { useState } from "react";
import { Button, Form, Row, Col, Select, Input, Space, Divider } from "antd";
import "bootstrap/dist/css/bootstrap.min.css";
import "antd/dist/antd.css";
import { PlusOutlined, MinusCircleOutlined } from "@ant-design/icons";
const { Option } = Select;

const Selector = () => {
  const [form] = Form.useForm();
  const [formFields, selectFormFields] = useState([]);
  const [rowCount, selectRowCount] = useState(0);
  function onChange(value) {
    console.log(`selected ${value}`);
  }

  const incrementRowCount = () => {
    selectRowCount(rowCount + 1);
  };

  const decrementRowCount = () => {
    selectRowCount(rowCount - 1);
  };

  const setToZero = () => {
    selectRowCount(0);
  };

  const populateFormFields = (value) => {
    selectFormFields((prevState) => [...prevState, value]);
  };

  const currentSelection = [];
  const resetFormFields = () => {
    for (let i = 0; i < 4; i++) {
      currentSelection.push(formFields[i]);
    }
    selectFormFields(currentSelection);
  };

  const myStyle = {
    marginTop: 8,
    marginLeft: 10,
  };

  console.log(rowCount);
  console.log(formFields);
  return (
    <>
      <div className="container">
        <div
          style={{
            margin: "auto",
            padding: 30,
            justifyContent: "center",
            textAlign: "center",
          }}
        >
          <h1>Welcome to our Query Selector!</h1>
          <div
            className="border mx-auto"
            style={{
              margin: "auto",
              padding: 30,
              justifyContent: "center",
              display: "block",
            }}
          >
            <p style={{ textAlign: "left", color: "red" }}>
              * After filling in the field, "value:", the enter key must be
              pressed before adding more clauses!
            </p>
            <Form
              name="dynamic_form_nest_item advanced_search"
              layout="inline"
              form={form}
              className="ant-advanced-search-form"
            >
              <Row>
                <Form.Item label="Select">
                  <Select
                    showSearch
                    placeholder="Player/Team/Season"
                    optionFilterProp="children"
                    onChange={onChange}
                    filterOption={(input, option) =>
                      option.children
                        .toLowerCase()
                        .indexOf(input.toLowerCase()) >= 0
                    }
                    onSelect={populateFormFields}
                  >
                    <Option value="player">Player</Option>
                    <Option value="team">Team</Option>
                    <Option value="season">Season</Option>
                  </Select>
                </Form.Item>

                <Form.Item label="Where">
                  <Select
                    showSearch
                    optionFilterProp="children"
                    onChange={onChange}
                    filterOption={(input, option) =>
                      option.children
                        .toLowerCase()
                        .indexOf(input.toLowerCase()) >= 0
                    }
                    onSelect={populateFormFields}
                  >
                    <Option value="jack">Jack</Option>
                    <Option value="lucy">Lucy</Option>
                    <Option value="tom">Tom</Option>
                  </Select>
                </Form.Item>

                <Form.Item label="Relational Operator:">
                  <Select
                    placeholder=">/>=/</<=/==/!="
                    showSearch
                    optionFilterProp="children"
                    onChange={onChange}
                    filterOption={(input, option) =>
                      option.children
                        .toLowerCase()
                        .indexOf(input.toLowerCase()) >= 0
                    }
                    onSelect={populateFormFields}
                  >
                    <Option value=">">{`>`}</Option>
                    <Option value="<">{`<`}</Option>
                    <Option value="=">{`=`}</Option>
                    <Option value="<=">{`<=`}</Option>
                    <Option value=">=">{`>=`}</Option>
                  </Select>
                </Form.Item>

                <Form.Item label="Value:">
                  <Input
                    placeholder="Number/Text"
                    onPressEnter={(e) => {
                      populateFormFields(e.target.value);
                    }}
                  ></Input>
                </Form.Item>
                <Divider />
                <Form.List name="additional_info">
                  {(fields, { add, remove }) => (
                    <>
                      {fields.map(({ key, name, fieldKey, ...restField }) => (
                        <Space
                          key={key}
                          style={{
                            display: "flex",
                            marginBottom: 8,
                            marginTop: 8,
                          }}
                          align="baseline"
                        >
                          <Form.Item
                            label="AND/OR"
                            {...restField}
                            name={[name, "operator"]}
                            fieldKey={[fieldKey, "operator"]}
                          >
                            <Select
                              placeholder="AND/OR"
                              onSelect={populateFormFields}
                            >
                              <Option value="and">AND</Option>
                              <Option value="or">OR</Option>
                            </Select>
                          </Form.Item>
                          <Form.Item
                            label="Field"
                            {...restField}
                            name={[name, "field1"]}
                            fieldKey={[fieldKey, "field1"]}
                          >
                            <Select
                              placeholder="AND/OR"
                              onSelect={populateFormFields}
                            >
                              <Option value="and">AND</Option>
                              <Option value="or">OR</Option>
                            </Select>
                          </Form.Item>
                          <Form.Item
                            label="Relational Operator"
                            {...restField}
                            name={[name, "field2"]}
                            fieldKey={[fieldKey, "field2"]}
                          >
                            <Select
                              placeholder=">/>=/</<=/==/!="
                              showSearch
                              optionFilterProp="children"
                              onChange={onChange}
                              w
                              filterOption={(input, option) =>
                                option.children
                                  .toLowerCase()
                                  .indexOf(input.toLowerCase()) >= 0
                              }
                              onSelect={populateFormFields}
                            >
                              <Option value=">">{`>`}</Option>
                              <Option value="<">{`<`}</Option>
                              <Option value="=">{`=`}</Option>
                              <Option value="<=">{`<=`}</Option>
                              <Option value=">=">{`>=`}</Option>
                            </Select>
                          </Form.Item>
                          <Form.Item
                            label="Value"
                            {...restField}
                            name={[name, "field3"]}
                            fieldKey={[fieldKey, "field3"]}
                          >
                            <Input
                              placeholder="Number/Text"
                              onPressEnter={(e) => {
                                populateFormFields(e.target.value);
                              }}
                            ></Input>
                          </Form.Item>
                          <MinusCircleOutlined
                            onClick={() => {
                              remove(name);
                              decrementRowCount();
                            }}
                          />
                        </Space>
                      ))}
                      <Form.Item>
                        {rowCount > 0 && (
                          <Button
                            type="dashed"
                            onClick={() => {
                              add();
                              incrementRowCount();
                            }}
                            style={myStyle}
                            block
                            icon={<PlusOutlined />}
                          >
                            Add Field
                          </Button>
                        )}
                        {rowCount == 0 && (
                          <Button
                            type="dashed"
                            onClick={() => {
                              add();
                              incrementRowCount();
                            }}
                            block
                            icon={<PlusOutlined />}
                          >
                            Add Field
                          </Button>
                        )}
                      </Form.Item>
                    </>
                  )}
                </Form.List>
              </Row>
              <Row style={{ marginTop: 8 }}>
                <Col
                  span={24}
                  style={{
                    textAlign: "right",
                  }}
                >
                  <Button
                    type="primary"
                    htmlType="submit"
                    onClick={() => {
                      console.log(JSON.stringify(formFields));
                    }}
                  >
                    Search
                  </Button>
                  <Button
                    style={{
                      margin: "0 8px",
                    }}
                    onClick={() => {
                      form.resetFields();
                      setToZero();
                      resetFormFields();
                    }}
                  >
                    Clear
                  </Button>
                </Col>
              </Row>
            </Form>
          </div>
        </div>
      </div>
    </>
  );
};

export default Selector;
