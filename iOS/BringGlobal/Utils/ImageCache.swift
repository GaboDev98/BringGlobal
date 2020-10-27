//
//  ImageCache.swift
//  BringGlobal
//
//  Created by Angel Gabriel Ascanio Duran on 22/10/20.
//

import UIKit
import SwiftUI
import Combine

class ImageCache {
    
    enum Error: Swift.Error {
        case dataConversionFailed
        case sessionError(Swift.Error)
    }
    
    static let shared = ImageCache()
    
    private let cache = NSCache<NSURL, UIImage>()
    
    private init() { }
    
    static func image(for url: URL) -> AnyPublisher<UIImage?, ImageCache.Error> {
    
        guard let image = shared.cache.object(forKey: url as NSURL) else {
            return URLSession
                .shared
                .dataTaskPublisher(for: url)
                .tryMap { (tuple) -> UIImage in
                    let (data, _) = tuple
                    guard let image = UIImage(data: data) else {
                        throw Error.dataConversionFailed
                    }
                    shared.cache.setObject(image, forKey: url as NSURL)
                    return image
                }
                .mapError({ error in Error.sessionError(error) })
                .eraseToAnyPublisher()
        }
        
        return Just(image)
            .mapError({ _ in fatalError() })
            .eraseToAnyPublisher()
    }
}

class ImageModel: ObservableObject {
    
    @Published var image: UIImage? = nil
    
    var cacheSubscription: AnyCancellable?
    
    init(url: URL) {
        cacheSubscription = ImageCache
            .image(for: url)
            .replaceError(with: nil)
            .receive(on: RunLoop.main, options: .none)
            .assign(to: \.image, on: self)
    }
}

struct RemoteImage : View {
    
    @ObservedObject var imageModel: ImageModel
    
    init(url: URL) {
        imageModel = ImageModel(url: url)
    }
    var body: some View {
        imageModel
            .image
            .map {
                Image(uiImage:$0)
                .resizable()
                .aspectRatio(contentMode: .fit)
            }
            ?? Image("Loading")
                .resizable()
                .aspectRatio(contentMode: .fit)
    }
}
