import { MapType } from "../db";

// TODO: move 'cn-3' to configuration
const ACH = "cn-3";

export function constructContentPath(param: {
  mapType: MapType;
  course: string;
  book: string;
  unit?: string;
}): string {
  if (param.course === "highflyers") {
    return param.mapType === MapType.UnitQuiz
      ? `highflyers/${ACH}/${mapHfBook(param.book)}/${param.unit}`
      : `highflyers/${ACH}/${mapHfBook(param.book)}`;
  }
  if (param.course === "TB") {
    return param.mapType === MapType.UnitQuiz
      ? `tb16/${ACH}/${mapTbBook(param.book)}/${param.unit}`
      : `tb16/${ACH}/${mapTbBook(param.book)}`;
  }
  if (param.course === "frontrunner") {
    return param.mapType === MapType.UnitQuiz
      ? `frontrunner/${ACH}/${mapFrBook(param.book)}/${param.unit}`
      : `frontrunner/${ACH}/${mapFrBook(param.book)}`;
  }

  throw new Error(`unknown course: ${param.course}`);
}

function mapHfBook(bookCode: string): string {
  switch (bookCode) {
    case "HFC":
      return "book-1";
    case "HFD":
      return "book-2";
    case "HFE":
      return "book-3";
    case "HFF":
      return "book-4";
    case "HFG":
      return "book-5";
    case "HFH":
      return "book-6";
    case "HFI":
      return "book-7";
    case "HFJ":
      return "book-8";
    default:
      throw new Error(`unknown book: ${bookCode}`);
  }
}
function mapTbBook(bookCode: string): string {
  switch (bookCode) {
    case "TBv3Bk1":
      return "book-1";
    case "TBv3Bk2":
      return "book-2";
    case "TBv3Bk3":
      return "book-3";
    case "TBv3Bk4":
      return "book-4";
    case "TBv3Bk5":
      return "book-5";
    case "TBv3Bk6":
      return "book-6";
    case "TBv3Bk7":
      return "book-7";
    case "TBv3Bk8":
      return "book-8";
    default:
      throw new Error(`unknown book: ${bookCode}`);
  }
}
function mapFrBook(bookCode: string): string {
  switch (bookCode) {
    case "FRa1.1":
      return "book-1";
    case "FRa1.2":
      return "book-2";
    case "FRa2.1":
      return "book-3";
    case "FRa2.2":
      return "book-4";
    case "FRb1.1.1":
      return "book-5";
    case "FRb1.1.2":
      return "book-6";
    case "FRb1.2.1":
      return "book-7";
    case "FRb1.2.2":
      return "book-8";
    case "FRb2.1.1":
      return "book-9";
    case "FRb2.1.2":
      return "book-10";
    case "FRb2.2.1":
      return "book-11";
    case "FRb2.2.2":
      return "book-12";
    case "FRc1.1.1":
      return "book-13";
    case "FRc1.1.2":
      return "book-14";
    case "FRc1.2.1":
      return "book-15";
    case "FRc1.2.2":
      return "book-16";
    default:
      throw new Error(`unknown book: ${bookCode}`);
  }
}
