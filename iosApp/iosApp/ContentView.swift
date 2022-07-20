//
//  ContentView.swift
//  Shared
//
//  Created by Ray Qu on 11/07/22.
//

import SwiftUI

struct ContentView: View {
    private let dataList = ["1", "2", "3"]

    var body: some View {
        List(dataList, id: \.self) { data in
            Text(data)
                .padding()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
