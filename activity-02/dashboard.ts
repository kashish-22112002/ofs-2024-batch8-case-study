/**
 * @license
 * Copyright (c) 2014, 2024, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */

  /**
   * Optional ViewModel method inv
   * oked after the View is inserted into the
   * document DOM.  The application can put logic that requires the DOM being
   * attached here.
   * This method might be called multiple times - after the View is created
   * and inserted into the DOM and after the View is reconnected
   * after being disconnected.
   */
  import * as AccUtils from "../accUtils";
  import * as ko from "knockout";
  import * as Bootstrap from "ojs/ojbootstrap";
  import "oj-c/input-number";
  import "oj-c/input-text";
  import "ojs/ojknockout";
  import "ojs/ojknockout";
  import Message = require("ojs/ojmessaging");
  import 'oj-c/input-password';
  import "ojs/ojlabel";
  import * as ResponsiveUtils from 'ojs/ojresponsiveutils';
  import { IntlConverterUtils } from 'ojs/ojconverterutils-i18n';
  import { ojDatePicker } from 'ojs/ojdatetimepicker';
  import 'ojs/ojknockout';
  import 'ojs/ojdatetimepicker';
  import 'ojs/ojlabel';
  import "oj-c/form-layout"
  
  
  class DashboardViewModel {
    firstname: ko.Observable<string> | ko.Observable<any>; // any means can have null values
    salary:ko.Observable<number> | ko.Observable<any>;
    password:ko.Observable<any> | ko.Observable<any>;
    date:ko.Observable<Date> | ko.Observable<any>;

    
    constructor() {
      this.firstname = ko.observable(null)
      this.salary = ko.observable(null);
      this.password=ko.observable(null);
      this.date=ko.observable(null);
    }
    
  }
export = DashboardViewModel;