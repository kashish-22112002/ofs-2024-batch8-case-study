import * as AccUtils from "../accUtils";
import * as ko from "knockout";
import * as Bootstrap from "ojs/ojbootstrap";
import "oj-c/input-number";
import "oj-c/input-text";
import "ojs/ojknockout";
import Message = require("ojs/ojmessaging");
import "oj-c/input-password";
import "ojs/ojlabel";
import "ojs/ojknockout";
import "ojs/ojdatetimepicker";
import "ojs/ojlabel";
import "oj-c/form-layout";
import "knockout";
import "ojs/ojknockout";
import "oj-c/button";
import "oj-c/message-banner";
import "ojs/ojknockout";
import "oj-c/message-banner";
import { whenDocumentReady } from "ojs/ojbootstrap";
import { MessageBannerItem, CMessageBannerElement } from "oj-c/message-banner";
import MutableArrayDataProvider = require("ojs/ojmutablearraydataprovider");
import "ojs/ojformlayout";
import "oj-c/input-text";
import "ojs/ojknockout";
import "oj-c/message-banner";
import "oj-c/button";
import "ojs/ojknockout";
import "oj-c/progress-bar";
import "ojs/ojbutton";

type DemoMessageBannerItem = MessageBannerItem & {
  id: string;
};

class DashboardViewModel {
  value: ko.Observable<string>;
  firstname: ko.Observable<string> | ko.Observable<any>; // any means can have null values
  salary: ko.Observable<number> | ko.Observable<any>;
  password: ko.Observable<any> | ko.Observable<any>;
  date: ko.Observable<Date> | ko.Observable<any>;
  button1: ko.Observable<any>;
  progressValue: ko.Observable<number>;

  readonly personalInformationMessages: MutableArrayDataProvider<
    string,
    DemoMessageBannerItem
  >;
  private counter: number;

  constructor() {
    this.value = ko.observable("Green");
    this.firstname = ko.observable(null);
    this.salary = ko.observable(null);
    this.password = ko.observable(null);
    this.date = ko.observable(null);
    this.button1 = ko.observable(null);
    this.counter = 0;
    this.progressValue = ko.observable(0);
  
    const initialPersonalSectionData = [
      {
        id: "message",
        severity: "confirmation",
        summary: "Updated personal information",
        detail:
          "The provided personal information of the employee has been updated in the database.",
        timestamp: new Date().toISOString(),
      },
    ];
    this.personalInformationMessages = new MutableArrayDataProvider(
      initialPersonalSectionData,
      {
        keyAttributes: "id",
      }
    );
  }

  public buttonClick = async (event: Event) => {
    const firstName = this.firstname();
    const salary = this.salary();
    this.progressValue(this.progressValue() + 100); // Increment progress value by 100
    alert(`Hi ${firstName}, your salary is ${salary}.`);
  };

  readonly closePersonalInformationMessage = (
    event: CMessageBannerElement.ojClose<string, DemoMessageBannerItem>
  ) => {
    // remove the message from the data to close it
    let data = this.personalInformationMessages.data.slice();
    const closeMessageKey = event.detail.key;

    data = data.filter((message) => (message as any).id !== closeMessageKey);
    this.personalInformationMessages.data = data;
  };
}

class ViewModel {
  private readonly step = ko.observable(0);
  readonly progressValue = ko.pureComputed(() => {
    return Math.min(this.step(), 100);
  });
  constructor() {
    window.setInterval(() => {
      this.step((this.step() + 1) % 200);
    }, 30);
  }
}
whenDocumentReady().then(() => {
  ko.applyBindings(
    new ViewModel(),
    document.getElementById("progressBarWrapper")
  );
});

export = DashboardViewModel;
