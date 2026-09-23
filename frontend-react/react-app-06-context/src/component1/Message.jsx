import { useContext } from "react";
import { MyContext } from "../App";

export default function Message() {
  const value = useContext(MyContext); //context의 value를 읽어 옴.
  return <div> Received : {value}</div>;
}
