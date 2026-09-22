import React, { useEffect } from "react";

export default function useEffectEx1() {
  useEffect(() => {
    console.log("화면이 랜더링 될 때마다 실행");
  });

  return <h1> Hello React </h1>;
}
