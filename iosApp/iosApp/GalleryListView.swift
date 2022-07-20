//
//  GalleryView.swift
//  iOSKMM
//
//  Created by Ray Qu on 13/07/22.
//

import SwiftUI
import SDWebImageSwiftUI

struct GalleryListView: View {


    @ObservedObject var viewModel: GalleryListViewModel

    
    init() {
        viewModel = GalleryListViewModel()
    }
    
    var body: some View {
        List(viewModel.galleries) { data in
            HStack {
                AnimatedImage(url: URL.init(string: data.downloadUrl))
                    .resizable()
                    .frame(width: 50, height: 50)
                    .clipShape(Circle())
                VStack(spacing: 16) {
                    Text(data.author)
                    Text(data.url)
                        .lineLimit(1)
                }
                .padding()
            }
           
        }
        .onAppear {
            viewModel.loadData()
        }
    
    }
}

struct GalleryView_Previews: PreviewProvider {
    static var previews: some View {
        GalleryListView()
    }
}
