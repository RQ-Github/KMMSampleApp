//
//  GalleryListViewModel.swift
//  iOSKMM
//
//  Created by Ray Qu on 13/07/22.
//

import Foundation
import shared

@MainActor class GalleryListViewModel: ObservableObject {
    private let repository = GalleryRepository(databaseDriverFactory: DatabaseDriverFactory())
    
    @Published var galleries: [Gallery] = []

    func loadData() {
        repository.get {[weak self] galleries, error in
            guard let galleries = galleries else { return }
            self?.galleries = galleries
        }
    }
}

extension Gallery : Identifiable {
    
}
