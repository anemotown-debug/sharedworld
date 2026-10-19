# SharedWorld for Minecraft 26.3 (Fabric)

Based on https://github.com/pmarinroig/sharedworld (MIT). Adds a `26.3` bucket
by copying the 26.2 bucket. NOT compiled yet: it was prepared without network access.

## Apply and build
```
git clone https://github.com/pmarinroig/sharedworld.git
cd sharedworld
git apply /path/to/sharedworld-26.3.patch
cd packages/fabric/modern
# put a Fabric e4mc jar for 26.3 in ../local/mods/ if you want to run it (optional for building)
./gradlew build -PsharedworldMcBucket=26.3
```
Requires JDK 25. The jar lands in `packages/fabric/modern/build/libs/`.

## What the patch changes
- buckets.json: new `26.3` bucket (Fabric API 0.161.0+26.3, range >=26.3 <27), 26.2 capped at <26.3, modern default now 26.3
- modern/gradle.properties: fabric-loader 0.19.3 -> 0.19.5
- src/versioned/26.3 and src/devhelperVersioned/26.3: copies of the 26.2 versions
- README bucket list

## Likely trouble spots if it fails to compile
The four files in src/versioned/26.3/java/link/sharedworld/versioned/
(ClientCompat, ServerPublishCompat, TabBarCompat, VersionedTab) plus the
mixins json. Those are the parts 26.2 had to special-case.
