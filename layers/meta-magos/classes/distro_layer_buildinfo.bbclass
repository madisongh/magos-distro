DISTRO_LAYER_REVTAG ?= "${@distro_layer_revtag(d)}"
DISTRO_LAYER_BRANCH ?= "${@distro_layer_branch(d)}"
DISTRO_LAYER_REVISION ?= "${@distro_layer_revision(d)}"

def dlb_prefixed_shorthash(path):
    if path is not None:
        f = os.popen("cd %s; git show -q --pretty='%%h' -- 2>/dev/null" % path)
        githash = f.read().strip()
        f.close()
        if len(githash) != 0:
            return "." + githash
    return ""

def dlb_git_revision(path):
    if path is not None:
        f = os.popen("cd %s; git show -q --pretty='%%H' -- 2>/dev/null" % path)
        githash = f.read().strip()
        f.close()
        return githash
    return ""

def dlb_git_branch(path):
    branchname = ""
    if path is not None:
        f = os.popen("cd %s; git branch -q 2>/dev/null" % path)
        for line in f:
            if line.startswith("* "):
                branchname = line[2:].strip()
                break
        f.close()
    return branchname

def distro_layername(d):
    return d.getVar("DISTRO_LAYER", True)

def distro_layerpath(lname, d):
    layers = {os.path.basename(l): l for l in (d.getVar("BBLAYERS", True) or "").split()}
    try:
        return layers[lname]
    except KeyError:
        return None

def distro_layer_revtag(d):
    return dlb_prefixed_shorthash(distro_layerpath(distro_layername(d), d))

def distro_layer_revision(d):
    return dlb_git_revision(distro_layerpath(distro_layername(d), d))

def distro_layer_branch(d):
    return dlb_git_branch(distro_layerpath(distro_layername(d), d))
